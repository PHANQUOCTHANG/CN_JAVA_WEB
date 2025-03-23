import "./PostUser.css";
import {useState} from "react";
import {Form, Button} from "react-bootstrap";
import {Eye, EyeSlash} from "react-bootstrap-icons";

const PostUsers = () => {

    const [formData, setFormData] = useState({
        email: "",
        username : "",
        fullname : "",
        password : "",
        phone : "",
    });
    const [showPassword, setShowPassword] = useState(false);


    const handleInputChange = (event) => {
        const {anme, value} = event.target;
        setFormData({
            ...formData,
            [username]:value,
        })
    }

    return (
        <>
            <div className="center-form">
                <h1>Login</h1>
                <Form>
                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="email"
                            name="email"
                            placeholder="Enter email"
                            value={formData.email}
                            onChange = {handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="username"
                            placeholder="Enter user name"
                            value={formData.username}
                            onChange = {handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="fullname"
                            placeholder="Enter full name"
                            value={formData.fullname}
                            onChange = {handleInputChange}
                        />
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type={showPassword ? "text" : "password"}
                            name="password"
                            placeholder="Enter password"
                            value={formData.password}
                            onChange = {handleInputChange}
                        />
                        <Button
                            variant="outline-secondary"
                            onClick={() => setShowPassword(!showPassword)}
                        >
                            {showPassword ? <EyeSlash /> : <Eye />}
                        </Button>
                    </Form.Group>

                    <Form.Group controlId="formBasicName">
                        <Form.Control
                            type="text"
                            name="phone"
                            placeholder="Enter phone"
                            value={formData.phone}
                            onChange = {handleInputChange}
                        />
                    </Form.Group>
                </Form>
            </div>
        </>
    )
}

export default PostUser