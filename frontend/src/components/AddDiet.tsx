import React, { useState } from "react";

interface AddDietProps {
    addDiet: (id: string,dietName: string) => void;
}

const AddDiet: React.FC<AddDietProps> = ({ addDiet }) => {
    const [newDiet, setNewDiet] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setNewDiet(e.target.value);
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        const token = localStorage.getItem("jwtToken");
        console.log(token)
        try {
            const response = await fetch("/api/diets/user", {
                method: "POST",
                headers: {
                    'Authorization': `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ name: newDiet }),
            });
            if (!response.ok) {
                throw new Error("Failed to add diet");
            }
            addDiet("", newDiet);
            setNewDiet("");
        } catch (error) {
            console.error("Error adding diet: ", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Add Diet"
                value={newDiet}
                onChange={handleChange}
            />
            <button type="submit">Add</button>
        </form>
    );
};

export default AddDiet;