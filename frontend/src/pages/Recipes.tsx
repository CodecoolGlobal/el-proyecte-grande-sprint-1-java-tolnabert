import React, {useState} from "react";
import RadioButton from "../components/RadioButton";
import RecipeCard from "../components/RecipeCard";
import useSimpleFetch from "../useSimpleFetch";
import {Recipe} from "../utils/types";
import "./Recipes.css"
import "./SortingOptions.css"
import {NavLink} from "react-router-dom";

function Recipes() {

    const [selected, setSelected] = useState({
        sortBy: "name",
        sortOrder: "desc",
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSelected({
            ...selected,
            [e.target.name]: e.target.value,
        });
    };

    const {
        data: recipes,
        error,
        isLoading,
    } = useSimpleFetch<Recipe[]>(`/api/recipes?sortBy=${selected.sortBy}&sortOrder=${selected.sortOrder}`, localStorage.getItem("jwtToken"));

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    console.log(recipes);

    return (

        <div>
            <div className="sorting-container">
                <div className="sorting-box">
                    <h2>Order By:</h2>
                    <div className="sorting-options">
                        <RadioButton
                            name="sortBy"
                            labelText="Name"
                            value="name"
                            onChange={handleChange}
                            checked={selected.sortBy === "name"}
                        />
                        <RadioButton
                            name="sortBy"
                            labelText="Creation Date"
                            value="createdAt"
                            onChange={handleChange}
                            checked={selected.sortBy === "createdAt"}
                        />
                    </div>
                </div>
                <div className="sorting-box">
                    <h2>Sort By:</h2>
                    <div className="sorting-options">
                        <RadioButton
                            name="sortOrder"
                            labelText="Ascending"
                            value="asc"
                            onChange={handleChange}
                            checked={selected.sortOrder === "asc"}
                        />
                        <RadioButton
                            name="sortOrder"
                            labelText="Descending"
                            value="desc"
                            onChange={handleChange}
                            checked={selected.sortOrder === "desc"}
                        />
                    </div>
                </div>
            </div>
            <div className="recipe-container">
                {recipes &&
                    recipes.map((recipe) => ( recipe &&
                        <NavLink to={`/single-recipe/${recipe.publicId}`}>
                            <RecipeCard key={recipe.publicId} recipe={recipe}/>
                        </NavLink>
                    ))}
            </div>
        </div>
    );
}

export default Recipes;
