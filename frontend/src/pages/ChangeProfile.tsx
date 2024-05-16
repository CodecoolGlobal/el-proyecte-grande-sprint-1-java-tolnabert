import { useState } from "react";
import FormRow from "../components/FormRow";

type UpdateState = {
  firstName: string;
  lastName: string;
  dateOfBirth: Date;
  email: string;
};

function ChangeProfile() {
  const [message, setMessage] = useState<string>("");
  const [loading, setIsLoading] = useState<boolean>(false);
  const [formData, setFormData] = useState<UpdateState>({
    firstName: "",
    lastName: "",
    dateOfBirth: new Date(),
    email: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const date = new Date(e.target.value);
    setFormData({
      ...formData,
      dateOfBirth: date,
    });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const jwtToken = localStorage.getItem("jwtToken");

    setIsLoading(true);
    setMessage("");
    try {
      const response = await fetch("/api/clients/user/change-profile", {
        method: "PATCH",
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setMessage("Profile update successful!");
        setFormData({
          firstName: "",
          lastName: "",
          dateOfBirth: new Date(),
          email: "",
        });
      } else {
        const data = await response.json();
        setMessage(data.message || "Failed to update profile");
      }
    } catch (error) {
      console.error("Error during profile update:", error);
      setMessage("An error occurred. Please try again later.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <FormRow
          type='text'
          name='firstName'
          labelText='First Name: '
          value={formData.firstName}
          onChange={handleChange}
          required
        />
        <FormRow
          type='text'
          name='lastName'
          labelText='Last Name: '
          value={formData.lastName}
          onChange={handleChange}
          required
        />
        <FormRow
          type='date'
          name='dateOfBirth'
          labelText='Date of Birth: '
          value={formData.dateOfBirth.toISOString().slice(0, 10)}
          onChange={(event) => handleDateChange(event)}
          required
        />
        <FormRow
          type='email'
          name='email'
          labelText='Email: '
          value={formData.email}
          onChange={handleChange}
          required
        />
        <button type='submit' disabled={loading}>
          {loading ? "Updating..." : "Update"}
        </button>
        {message && <p>{message}</p>}
      </form>
    </>
  );
}

export default ChangeProfile;
