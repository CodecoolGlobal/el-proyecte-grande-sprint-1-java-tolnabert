import React, { useState } from "react";
import FormRow from "../components/FormRow";
import { Link, useNavigate } from "react-router-dom";

type LoginState = {
  username: string;
  password: string;
};

function Login() {
  const navigate = useNavigate();
  const [message, setMessage] = useState<string>("");
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [formData, setFormData] = useState<LoginState>({
    username: "",
    password: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch("/api/clients/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const data = await response.json();
        
        localStorage.setItem("jwtToken", data.jwt);
        localStorage.setItem("roles", data.roles);
        localStorage.setItem("username", data.username);
        navigate("/");
      } else {
        const data = await response.json();
        setMessage(data.message || "Failed to login");
      }
    } catch (error) {
      console.error("Error during login:", error);
      setMessage("An error occurred during registration. Please try again later.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <FormRow
          type='text'
          name='username'
          labelText='Username: '
          value={formData.username}
          onChange={handleChange}
          required
        />
        <FormRow
          type='password'
          name='password'
          labelText='Password: '
          value={formData.password}
          onChange={handleChange}
          required
        />
        <button type='submit' disabled={isLoading}>
          {isLoading ? "Logging in..." : "Login"}
        </button>
        {message && <p>{message}</p>}
      </form>
      <p>
        Not a member yet? <Link to='/register'>register</Link>
      </p>
    </>
  );
}
export default Login;
