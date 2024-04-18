import useFetch from "../hooks";
import { Recipe } from "../utils/types";

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
      {/* to do on backend  */}
      <p>filter & sort & pagination</p>
      {recipes &&
        recipes.map((recipe) => {
          return <p key={recipe.id}>{recipe.name}</p>;
        })}
    </div>
  );
}
export default Recipes;
