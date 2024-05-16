import {Recipe} from "../utils/types.ts";
import "./css/RecipeDetails.css";

interface Props {
    recipe: Recipe;
}

function SingleRecipeDetails({recipe}: Props) {
    return (
        <div className="recipe-details">
            <h1>{recipe.name}</h1>
            <img src={recipe.image} alt={recipe.name} className="recipe-image"/>
            <div className="recipe-info">
                <h2>Description</h2>
                <p>{recipe.description}</p>

                <h2>Ingredients</h2>
                <ul>
                    {recipe.ingredients.map((ingredient, index) => (
                        <li key={index}>{ingredient}</li>
                    ))}
                </ul>

                <h2>Steps</h2>
                <ol>
                    {recipe.steps.map((step, i) => (
                        <li key={i}>{step}</li>
                    ))}
                </ol>

                <p>Diets: {recipe.diets.join(", ")}</p>
                <p>Created By: {recipe.createdBy.username}</p>
                <p>Created At: {new Date(recipe.createdAt).toLocaleDateString()}</p>
            </div>
        </div>
    )
}

export default SingleRecipeDetails