import { useEffect, useState } from 'react';
import Chat from 'react-simple-chat';
import 'react-simple-chat/src/components/index.css';

const Messenger = () => {
    // 변수 선언
    const [messages, setMessages] = useState([]);
    const [uuid, setUuid] = useState("");

    // 함수 선언
    const getAnswer = (message) => {
        setMessages([...messages, message]);
        const url = `http://localhost:8080/chatbot/chat/message`;
        fetch(url, {method:"POST", body: JSON.stringify({question:message, uuid:uuid}), headers:{"Access-Control-Allow-Origin":"*", "Content-Type":"application/json"} })
            .then((res) => res.json())
            .then((data) => {
                console.log(data.uuid);
                setMessages(messages => [...messages, data]); // 답변
            }).catch(() => {
                console.log("에러발생");
            });
    };

    const openChat = () => {
        // fetch 메소드 구현
        const url = `http://localhost:8080/chatbot/chat/open`;
        fetch(url, {method:"POST", headers:{"Access-Control-Allow-Origin":"*", "Content-Type":"application/json"} })
            .then((res) => res.json())
            .then((data) => {
                setUuid(data.uuid);
                setMessages(messages => [...messages, data]);
            })
            .catch(() => {
                console.log("에러발생");
            });
    };

    useEffect(openChat, []);

    return(
        <Chat
            title="챗봇 샘플"
            user={{ id: "chatbot" }}
            messages={messages}
            onSend={ question => getAnswer(question) }
        />
    );
};

export default Messenger;