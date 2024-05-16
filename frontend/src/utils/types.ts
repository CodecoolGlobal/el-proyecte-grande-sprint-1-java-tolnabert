export type User = {
  id: string;
  username: string;
  firstName: string;
  lastName: string;
  dateOfBirth: Date;
  email: string;
  ownRecipes: Recipe[];
  favoredRecipes: Recipe[];
};

export type Recipe = {
  id: string;
  name: string;
  description: string;
  diets: Diet[];
  ingredients: string[];
  steps: string[];
  portions: number;
  image: string;
  createdBy: User;
  createdAt: Date;
};


export type Diet = {
  publicId: string;
  name: string;
  isChecked: boolean;
}

export type DietDTO = {
  id: string;
  name: string;
}

export type Unit = {
  unitId: string,
  unitName: string;
}

export type Step = {
  step: string;
}

export interface Ingredient {
  name: string;
  unit: Unit;
  portions: number;
}
