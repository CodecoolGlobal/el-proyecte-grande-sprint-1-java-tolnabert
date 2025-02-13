import React, { useEffect, useState } from "react";
import FormRow from "../components/FormRow";
import {Diet, Ingredient, Step} from "../utils/types";
import { Unit } from "../utils/types";
import Checkbox from "../components/Checkbox";
import AddDiet from "../components/AddDiet.tsx";
import AddUnit from "../components/AddUnit.tsx";
import AddIngredient from "../components/AddIngredient.tsx";
import AddSteps from "../components/AddSteps.tsx";
import "../css/createRecipe.css"


function CreateRecipe() {
  const [createForm, setCreateForm] = useState({
    name: "",
    description: "",
    diets: [] as Diet[],
    ingredients: [] as Ingredient[],
    units: [] as Unit[],
    steps: [] as Step[],
    portions: 0,
    image: "",
    createdBy: "",
  });

  useEffect(() => {
    async function getDiets(){
      const token = localStorage.getItem("jwtToken");
      try {
        const response = await fetch("api/diets", {
          headers: {
            'Authorization': `Bearer ${token}`,
          }
        });
        if (!response.ok) {
          throw new Error("Failed to fetch diets");
        }
        const diets: Diet[] = await response.json();
        console.log("getDiets: ", diets)
        setCreateForm((prevForm) => ({
          ...prevForm,
          diets: diets,
        }));
      } catch (error) {
        console.error("Error fetching diets: ", error);
      }
    }
    getDiets();
    getUnits();
  }, []);



  const getUnits = async () => {
    const token = localStorage.getItem("jwtToken");
    try {
      const response = await fetch("/api/units", {
            headers: {
              'Authorization': `Bearer ${token}`,
              }
            }
          );
      if (!response.ok) {
        throw new Error("Failed to fetch units");
      }

      const units: Unit[] = await response.json();
      setCreateForm((prevForm) => ({
        ...prevForm,
        units: units,
      }))
    } catch (error) {
      console.error("Error fetching units: ", error);
    }
  }


  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name} = e.target;
    setCreateForm(prevForm => ({
      ...prevForm,
      [name]: e.target.value,
    }));
    console.log(name, e.target.value);
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const portion = parseInt(String(createForm.portions))
    try {
      const checkedDiets = createForm.diets
          .filter((diet) => diet.isChecked)
          .map(({ publicId, name }) => ({ publicId, name }));
      console.log("checkedDiets", checkedDiets)
      const recipeData = {
        ...createForm,
        portions: portion,
        diets: checkedDiets,
        createdBy: "d4f8915a-599d-4dd3-a8c3-034f7fa946c4",
      };

      console.log(recipeData)


      const token = localStorage.getItem("jwtToken")
      const response = await fetch("/api/recipes/user", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify(recipeData),
      });
      if (!response.ok) {
        throw new Error("Failed to create recipe");
      }
      console.log("Recipe created successfully!");
      setCreateForm({
        name: "",
        description: "",
        diets: [],
        ingredients: [] as Ingredient[],
        units: [],
        steps: [] as Step[],
        portions: 0,
        image: "",
        createdBy: "",
      });
    } catch (error) {
      console.error("Error creating recipe: ", error);
    }
  };

  const handleCheckChildElement = (
      index: number,
      isChecked: boolean
  ) => {
    setCreateForm((prevForm) => ({
      ...prevForm,
      diets: prevForm.diets.map((diet, i) =>
          i === index ? { ...diet, isChecked } : diet
      ),
    }));
  };

  const handleAddDiet = (dietName: string, id:string) => {
    console.log(dietName)
    setCreateForm((prevForm) => ({
      ...prevForm,
      diets: [...prevForm.diets, { publicId:id, name: dietName, isChecked: false }],
    }));
  };

  const handleAddUnit = ( name: string, id:string) => {
    setCreateForm((prevForm) => ({
      ...prevForm,
      units: [...prevForm.units, {publicId: id, unitName: name }],
    }));
  };

  const handleAddIngredient = (ingredient: Ingredient) => {
    setCreateForm(prevForm => ({
      ...prevForm,
      ingredients: [...prevForm.ingredients, ingredient],
    }));
  };

  const handleAddStep = (step: Step) => {
    setCreateForm(prevForm => ({
      ...prevForm,
      steps: [...prevForm.steps, step],
    }))
  };

  const transformIngredient = (ingredients: Ingredient[] ): string => {
    if(!ingredients.length){
      return "";
    } else {
      return ingredients.map((ingredient) => `${ingredient.name} - ${ingredient.quantity} ${ingredient.unit?.unitName}`).join(", ");
    }
  }

  const transformStep = (steps: Step[]): string => {
    if(!steps.length){
      return "";
    } else {
      return steps.map((step, index) => `${index + 1}. ${step.stepDescription}`).join(", ");
    }
  };

  return (
      <>
        <form onSubmit={handleSubmit}>
          <FormRow
              type="text"
              name="name"
              labelText="Name: "
              value={createForm.name}
              onChange={handleChange}
              required
          />
          <FormRow
              type="text"
              name="description"
              labelText="Description: "
              value={createForm.description}
              onChange={handleChange}
              required
          />
          <p>Diets (add new diet below)</p>
          <ul>
            {createForm.diets && createForm.diets.map((diet, index) => (
                <Checkbox
                    key={diet.publicId}
                    index={index}
                    handleCheckChildElement={handleCheckChildElement}
                    value={diet.name}
                    isChecked={diet.isChecked}
                />
            ))}
          </ul>
          <div>
            <label htmlFor="ingredients" className="ingredients-label">Ingredients</label>
            <textarea
                name="ingredients"
                value={transformIngredient(createForm.ingredients)}
                onChange={handleChange}
                disabled={true}
                required
            />
          </div>
          <AddIngredient addIngredient={handleAddIngredient} unitsVisible={createForm.units} />
          <label htmlFor="steps" className="step-label">Steps</label>
          <textarea
              name="steps"
              value={transformStep(createForm.steps)}
              onChange={handleChange}
              disabled={true}
              required
          />
          <AddSteps addStep={handleAddStep}/>
          <FormRow
              type="number"
              name="portions"
              labelText="Serves: "
              value={createForm.portions}
              onChange={handleChange}
              required
          />
          <FormRow
              type="text"
              name="image"
              labelText="Image URL: "
              value={createForm.image}
              onChange={handleChange}
              required
          />
          <button type="submit">Send New Recipe</button>
        </form>
        <div className="add-section">
          <p className="add-section">Add new diet</p>
          <AddDiet addDiet={handleAddDiet}/>
        </div>
        <div className="add-section">
          <p className="add-section">Add new unit</p>
          <AddUnit addUnit={handleAddUnit}/>
        </div>
      </>
  );
}

export default CreateRecipe;
