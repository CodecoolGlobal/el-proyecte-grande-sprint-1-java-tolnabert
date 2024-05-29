interface CheckboxProps {
    id?: string;
    index: number;
    handleCheckChildElement?(index: number, isChecked: boolean): void;
    isChecked?: boolean;
    value?: string;
}

const Checkbox = (props: CheckboxProps) => {
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const isChecked = event.target.checked;
        if (props.handleCheckChildElement) {
            props.handleCheckChildElement(props.index, isChecked);
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