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
  publicId: string;
  name: string;
  description: string;
  diets: string[];
  ingredients: string[];
  steps: string[];
  portions: number;
  image: string;
  createdBy: User;
  createdAt: Date;
};


export type Diet = {
  name: string;
  isChecked: boolean;
}

export type Unit = {
  name: string;
}

export type Step = {
  step: string;
}

export interface Ingredient {
  name: string;
  unit: string;
  portions: number;
}
