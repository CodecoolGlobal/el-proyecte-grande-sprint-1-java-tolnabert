import RecipeCard from "../components/RecipeCard";
import useFetch from "../hooks";
import { Recipe } from "../utils/types";
import "./Recipes.css"

function Recipes() {
  const {
    data: recipes,
    error,
    isLoading,
  } = useFetch<Recipe[]>("/api/recipes");

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  console.log(recipes);

  return (
    <div>
        <p>filter & sort & pagination</p>
      <div className="recipe-container">
        {/* to do on backend  */}
        {recipes &&
          recipes.map((recipe) => (
            <RecipeCard key={recipe.id} recipe={recipe} />
          ))}
      </div>
    </div>
  );
}
export default Recipes;
