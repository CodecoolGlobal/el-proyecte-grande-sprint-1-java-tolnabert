import React, { useEffect, useState } from "react";
import FormRow from "../components/FormRow";
import { Diet } from "../utils/types";
import Checkbox from "../components/Checkbox";
import AddDiet from "../components/AddDiet.tsx";

function CreateRecipe() {
  const [createForm, setCreateForm] = useState({
    name: "",
    description: "",
    diets: [] as Diet[],
    ingredients: "",
    steps: "",
    portions: 0,
    image: "",
    createdBy: "",
  });

  useEffect(() => {
    getDiets();
  }, []);

  const getDiets = async () => {
    try {
      const response = await fetch("api/diets");
      if (!response.ok) {
        throw new Error("Failed to fetch diets");
      }
      const diets: Diet[] = await response.json();
      setCreateForm((prevForm) => ({
        ...prevForm,
        diets: diets,
      }));
    } catch (error) {
      console.error("Error fetching diets: ", error);
    }
  };

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
    try {
      const checkedDiets = createForm.diets.filter((diet) => diet.isChecked).map((diet) => diet.name);
      const recipeData = {
        ...createForm,
        diets: checkedDiets,
      };

      console.log(recipeData)

      const response = await fetch("/api/recipes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
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
        ingredients: "",
        steps: "",
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

  const handleAddDiet = (dietName: string) => {
    setCreateForm((prevForm) => ({
      ...prevForm,
      diets: [...prevForm.diets, { name: dietName, isChecked: false }],
    }));
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
          <p>Diets(add new diet below)</p>
          <ul style={{listStyle: "none"}}>
            {createForm.diets.map((diet: Diet, index) => (
                <Checkbox
                    key={index}
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
              value={createForm.ingredients}
              onChange={handleChange}
              required
            />
          </div>
          <FormRow
              type="textarea"
              name="ingredients"
              labelText="Ingredients: "
              value={createForm.ingredients}
              onChange={handleChange}
              required
          />
          <FormRow
              type="textarea"
              name="steps"
              labelText="Steps: "
              value={createForm.steps}
              onChange={handleChange}
              required
          />
          <FormRow
              type="number"
              name="portions"
              labelText="Portions: "
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
        <p>Add new diet</p>
        <AddDiet addDiet={handleAddDiet}/>
      </>
  );
}

export default CreateRecipe;
