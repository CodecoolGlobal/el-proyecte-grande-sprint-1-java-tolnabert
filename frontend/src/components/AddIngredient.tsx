import React, { useEffect, useState} from "react";
import {Ingredient, Unit} from "../utils/types.ts";



interface AddIngredientProps {
    addIngredient: (ingredient: Ingredient) => void;
}

const defaultUnitState = {
    publicId: "",
    unitName: ""
};

function AddIngredient({ addIngredient }: AddIngredientProps) {
    const [units, setUnits] = useState<Unit[]>([] );
    const [unitToAdd, setUnitToAdd] = useState<Unit>(defaultUnitState);
    const [ingredientName, setIngredientName] = useState<string>("");
    const [quantity, setQuantity] = useState<number>(0);

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
        if (!ingredientName || !ingredientName.length || !quantity) {
            return;
        }
        const ingredient: Ingredient = {
            name: ingredientName,
            quantity: quantity,
            unit: unitToAdd === defaultUnitState ? units[0] : unitToAdd,
        }
        addIngredient(ingredient);
        setIngredientName("");
        setQuantity(0);
        setUnitToAdd(defaultUnitState);
    }

    const handleUnitChange = ( event: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedUnit: Unit | undefined = units.find(unit => unit.publicId === event.target.value);
        setUnitToAdd(selectedUnit!);
        console.log("unit to add:  " + selectedUnit?.unitName);
    }

    const handleIngredientNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setIngredientName(event.target.value);
    }

    const handlePortionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setQuantity(parseInt(event.target.value));
    };
    return (
        <div>
            <label>Ingredient name: </label>
            <input type="text" onChange={handleIngredientNameChange} value={ingredientName}/>
            <label>Portions:</label>
            <input type="number" onChange={handlePortionChange} value={quantity}/>
            <label>Choose unit:</label>
            <select name="unit" onChange={(event) => handleUnitChange(event)}>
                {Array.isArray(units) && units.map((unit) => (
                    <option key={unit.publicId} value={unit.publicId} id={unit.publicId}>
                        {unit.unitName}
                    </option>
                ))}
            </select>
            <button onClick={handleSubmitIngredient}>Add Ingredient</button>
        </div>
    );
}

export default AddIngredient;