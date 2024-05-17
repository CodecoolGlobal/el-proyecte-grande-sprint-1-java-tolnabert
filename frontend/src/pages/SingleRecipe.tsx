import useFetch from "../hooks.ts";
import {Recipe} from "../utils/types.ts";
import {useParams} from "react-router-dom";
import SingleRecipeDetails from "../components/SingleRecipeDetails.tsx";

function SingleRecipe() {

    const {id} = useParams();
    console.log(id)


    const {
        data: recipe,
        error,
        isLoading,
    } = useFetch<Recipe>(`/api/recipes/${id}`, localStorage.getItem("jwtToken"));


    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <div className="single-recipe">
            <SingleRecipeDetails key={id} recipe={recipe}/>
        </div>
    )
}

export default SingleRecipe