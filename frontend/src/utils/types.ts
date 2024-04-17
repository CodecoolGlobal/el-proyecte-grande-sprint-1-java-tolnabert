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
  ingredients: string[];
  steps: string[];
  portions: number;
  image: string;
  createdBy: User;
  createdAt: Date;
};
