import React, { ChangeEvent, FC } from "react";

type RadioButtonProps = {
  name: string;
  labelText?: string;
  value?: string | null;
  checked: boolean,
  onChange?: (event: ChangeEvent<HTMLInputElement>) => void,
}

const RadioButton: FC<RadioButtonProps> = ({
  name,
  labelText,
  value,
  checked,
  onChange,
}) => {
  return (
    <div className='form-row'>
      <label htmlFor={name}>
        {labelText || name}
      </label>
      <input
        className='form-input'
        type={"radio"}
        id={name}
        name={name}
        value={value ?? ""}
        checked={checked}
        onChange={onChange}
      />
    </div>
  );
};

export default RadioButton;