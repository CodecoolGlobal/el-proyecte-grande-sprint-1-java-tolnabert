import FormRow from "./FormRow";
import { PasswordChange } from "../utils/types";

type ChangePasswordProps = {
  dialogRef: React.MutableRefObject<HTMLDialogElement | null>;
  formData: PasswordChange;
  isLoading: boolean;
  message: string;
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleSubmit: (e: React.FormEvent<HTMLFormElement>) => Promise<void>;
  closeDialog: () => void;
};

function ChangePassword({
  dialogRef,
  formData,
  isLoading,
  message,
  handleChange,
  handleSubmit,
  closeDialog,
}: ChangePasswordProps) {
  return (
    <dialog ref={dialogRef} className='modal'>
      <h2>Change Password</h2>
      <form onSubmit={handleSubmit}>
        <FormRow
          type='password'
          name='oldPassword'
          labelText='Old password: '
          value={formData.oldPassword}
          onChange={handleChange}
          required
        />
        <FormRow
          type='password'
          name='newPassword'
          labelText='New password: '
          value={formData.newPassword}
          onChange={handleChange}
          required
        />
        <FormRow
          type='password'
          name='confirmationPassword'
          labelText='Confirmation password: '
          value={formData.confirmationPassword}
          onChange={handleChange}
          required
        />
        <button type='submit' disabled={isLoading}>
          {isLoading ? "Updating..." : "Update Password"}
        </button>
        {message && <p>{message}</p>}
      </form>
      <button className='close-button' onClick={closeDialog}>
        Close
      </button>
    </dialog>
  );
}

export default ChangePassword;
