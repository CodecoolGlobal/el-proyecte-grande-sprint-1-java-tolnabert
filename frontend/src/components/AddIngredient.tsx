import React, { useEffect, useState} from "react";
import {Ingredient, Unit} from "../utils/types.ts";



interface AddIngredientProps {
    addIngredient: (ingredient: Ingredient) => void;
}

function AddIngredient({ addIngredient }: AddIngredientProps) {
    const [units, setUnits] = useState<Unit[]>([] );
    // get the the publicId from the unit
    //const [unitId, setUnitId] = useState<string>();
    // actual unit object
    const [unitToAdd, setUnitToAdd] = useState<Unit>({
        publicId: "",
        unitName: ""
    });
    const [ingredientName, setIngredientName] = useState<string>("");
    const [portions, setPortions] = useState<number>(0);

    useEffect(() => {
        async function fetchUnits() {
            const token = localStorage.getItem("jwtToken");
            try {
                const response = await fetch("/api/units", {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    }
                });
                const data = await response.json();
                console.log("From addingredient", data)
                setUnits(data);
            } catch (error) {
                console.error("Error fetching units:", error);
            }
        }

        fetchUnits();
    }, []);

    const handleSubmitIngredient = async () => {
        console.log("AddIngredient");

        // implement fetch unit
        //const response = await fetch(`/api/units/${unitToAdd}`, {

        //})

        const ingredient: Ingredient = {
            name: ingredientName,
            portions: portions,
            // point the unit with publicId
            unit: unitToAdd,
        }
        addIngredient(ingredient);
        setIngredientName("");
        setPortions(0);
        setUnitToAdd({
            publicId: "",
            unitName: ""
        });
    }

    const handleUnitChange = ( event: React.ChangeEvent<HTMLSelectElement>) => {
        setUnitToAdd({
            publicId: event.target.id,
            unitName: event.target.value
        });
        console.log("unit to add:  " + event.target.accessKey);
    }

    const handleIngredientNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setIngredientName(event.target.value);
    }

    const handlePortionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPortions(parseInt(event.target.value));
    };
    return (
        <div>
            <label>Ingredient name: </label>
            <input type="text" onChange={handleIngredientNameChange} value={ingredientName}/>
            <label>Portions:</label>
            <input type="number" onChange={handlePortionChange} value={portions}/>
            <label>Choose unit:</label>
            <select name="unit" onChange={(event) => handleUnitChange(event)}>
                {Array.isArray(units) && units.map((unit) => (
                    <option key={unit.publicId} value={unit.unitName} id={unit.publicId}>
                        {unit.unitName}
                    </option>
                ))}
            </select>
            <button onClick={handleSubmitIngredient}>Add Ingredient</button>
        </div>
    );
}

export default AddIngredient;