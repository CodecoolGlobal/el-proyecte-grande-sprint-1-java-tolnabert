import { NavLink } from "react-router-dom";
import "../css/header.css";
import Logo from "./Logo";

function Header() {
  return (
      <div className='header'>
      <Logo />
        <NavLink className='navlink-button' to={"/register"}>
          Register
        </NavLink>
        <NavLink className='navlink-button' to={"/login"}>
          Login
        </NavLink>
      </div>
  );
}
export default Header;
