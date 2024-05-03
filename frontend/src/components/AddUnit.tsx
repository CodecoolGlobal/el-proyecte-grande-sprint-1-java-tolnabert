import React, { useState } from "react";

interface AddUnitProps {
    addUnit: (unitName: string) => void;
}

const AddUnit: React.FC<AddUnitProps> = ({ addUnit }) => {
    const [newUnit, setNewUnit] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setNewUnit(e.target.value);
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await fetch("/api/units", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ unitName: newUnit }),
            });
            if (!response.ok) {
                throw new Error("Failed to add diet");
            }
            addUnit(newUnit);
            setNewUnit("");
        } catch (error) {
            console.error("Error adding unit: ", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Add Unit"
                value={newUnit}
                onChange={handleChange}
            />
            <button type="submit">Add</button>
        </form>
    );
};

export default AddUnit;