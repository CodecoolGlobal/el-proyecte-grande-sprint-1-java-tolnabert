import {useState} from "react";
import {Step} from "../utils/types.ts";


interface AddStepProps {
    addStep: (step: Step) => void;
}

const AddSteps = ({ addStep }: AddStepProps) => {
    const [addNextStep, setAddNextStep] = useState("");


    const handleClick = () => {
        const step:Step = {
            step: addNextStep
        }
        addStep(step);
    };

    return (
        <div>
            <label htmlFor="nextStep">Add a Step</label>
            <input type="text" value={addNextStep} onChange={(e) => setAddNextStep(e.target.value)}/>
            <button onClick={handleClick}>Add</button>
        </div>
    );
};

export default AddSteps;