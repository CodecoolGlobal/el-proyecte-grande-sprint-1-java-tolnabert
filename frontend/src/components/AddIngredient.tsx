import React, { useEffect, useState} from "react";
import {Ingredient} from "../utils/types.ts";



interface AddIngredientProps {
    addIngredient: (ingredient: Ingredient) => void;
}

function AddIngredient({ addIngredient }: AddIngredientProps) {
    const [units, setUnits] = useState<string[]>(["ml", "dl", "l"]);
    const [unitToAdd, setUnitToAdd] = useState<string>("");
    const [ingredientName, setIngredientName] = useState<string>("");
    const [portions, setPortions] = useState<number>(0);

    useEffect(() => {
        async function fetchUnits() {
            try {
                const response = await fetch("http://localhost:8080/api/units");
                const data = await response.json();
                setUnits(data);
            } catch (error) {
                console.error("Error fetching units:", error);
            }
        }

        fetchUnits();
    }, []);

    const handleSubmitIngredient = () => {
        console.log("AddIngredient");
        const ingredient: Ingredient = {
            name: ingredientName,
            portions: portions,
            unit: unitToAdd,
        }
        addIngredient(ingredient);
        setIngredientName("");
        setPortions(0);
        setUnitToAdd("");
    }

    const handleUnitChange = ( event: React.ChangeEvent<HTMLSelectElement>) => {
        setUnitToAdd(event.target.value);
        console.log(unitToAdd)
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
                {units.map((unit, index) => (
                    <option key={index} value={unit}>
                        {unit}
                    </option>
                ))}
            </select>
            <button onClick={handleSubmitIngredient}>Add Ingredient</button>
        </div>
    );
}

export default AddIngredient;