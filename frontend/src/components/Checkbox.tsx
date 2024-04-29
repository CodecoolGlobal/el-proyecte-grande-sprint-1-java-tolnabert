interface CheckboxProps {
    id?: string;
    index: number; // Define index prop
    handleCheckChildElement?(index: number, isChecked: boolean): void;
    isChecked?: boolean;
    value?: string;
}

const Checkbox = (props: CheckboxProps) => {
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const isChecked = event.target.checked;
        if (props.handleCheckChildElement) {
            props.handleCheckChildElement(props.index, isChecked); // Use props.index
        }
    };

    return (
        <li>
            <input
                key={props.id}
                onChange={handleChange}
                type="checkbox"
                checked={props.isChecked ?? false}
                value={props.value}
            />
            {props.value}
        </li>
    );
};

export default Checkbox;