import {Recipe} from "../utils/types";
import "./css/RecipeCard.css"

interface Props {
    recipe: Recipe;
}

const RecipeCard = ({recipe}: Props) => {
    return (
        <div className="recipe-card">
            <img src={recipe.image}/>
            <div className="recipe-card-title">
                <h2>{recipe.name}</h2>
                <h3>Description: {recipe.description}</h3>
            </div>
        </div>
    );
};

export default RecipeCard;