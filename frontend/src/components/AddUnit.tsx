import React, { useState } from "react";

interface AddUnitProps {
    addUnit: (unitName: string, unitId: string) => void;
}

const AddUnit: React.FC<AddUnitProps> = ({ addUnit }) => {
    const [newUnit, setNewUnit] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setNewUnit(e.target.value);
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const token = localStorage.getItem("jwtToken");
        try {
            const response = await fetch("/api/units", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({ unitName: newUnit }),
            });
            if (!response.ok) {
                throw new Error("Failed to add unit");
            }
            const { unitId } = await response.json(); // Assuming the backend returns the generated unit ID
            addUnit(newUnit, unitId);
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