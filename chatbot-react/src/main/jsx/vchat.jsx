import { useEffect, useState } from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import IconButton from '@mui/material/IconButton';
import SendIcon from '@mui/icons-material/Send';
import { MessageList } from 'react-chat-elements';
import { Input } from 'react-chat-elements';
import 'react-chat-elements/dist/main.css';
import Vocal from '@untemps/react-vocal'

const Vchat = ({location}) => {

    // 1. 변수 선언(상태)
    const [messages, setMessages] = useState([]);   // 메시지 목록
    const [uuid, setUuid] = useState("");           // ETRI 오픈챗 uuid
    const [result, setResult] = useState("");       // STT
    const [question, setQuestion] = useState("");   // 질문

	const _onVocalStart = () => {
		setResult('')
	}

	const _onVocalResult = (result) => {
		setResult(result)
	}
    
    const tts = (url) => {
        var audio = document.createElement("Audio");
        audio.src = "http://localhost:8080/chatbot/resources/tts/"+url; 
        //window.URL.createObjectURL(stream);
        audio.play();
    }

    // 2. 함수 선언
    const getAnswer = () => {

        const answer = {
            position: 'right',
            type: 'text',
            text:question,
            date:new Date()
        };

        setMessages([...messages, answer]);
        const url = `http://localhost:8080/chatbot/chat/message`;
        fetch(url, {method:"POST", body: JSON.stringify({question:answer, uuid:uuid}), headers:{"Access-Control-Allow-Origin":"*", "Content-Type":"application/json"} })
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                tts(data.ttsUrl);
                setMessages(messages => [...messages, data]); // 답변
            }).catch(() => {
                console.log("에러발생");
            });
    };

    const openChat = () => {

        const store = location.props &&location.props.store;

        const url = `http://localhost:8080/chatbot/chat/open`;
        fetch(url, {method:"POST", body:JSON.stringify({store:store}), headers:{"Access-Control-Allow-Origin":"*", "Content-Type":"application/json"} })
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setUuid(data.uuid);
                setMessages(messages => [...messages, data]);
            })
            .catch(() => {
                alert("서버 켜있는지 확인");
            });
    };

    useEffect(openChat, []);
    // 3. html 코드 렌더링
    const handClick = () =>{
        getAnswer();
    }

    const handClickPress=(e)=>{
        if(e.key==='Enter'){
            handClick();
            e.target.value="";
        }
    }


    return(
        <Card sx={{height:'96vh', marginTop:'1vh'}}>
            <CardContent style={{backgroundColor:'lightgreen', height:'82vh'}}>
                <MessageList
                    className='message-list'
                    lockable={true}
                    toBottomHeight={'100%'}
                    dataSource={messages}
                />
            </CardContent>
            <CardContent>
                <Input
                    onKeyPress={handClickPress}
                    placeholder="챗봇에게 메시지 보내기"
                    multiline={false}
                    defaultValue={result}
                    onChange={(e)=>setQuestion(e.target.value)}
                    leftButtons={
                        <Vocal
                            onStart={_onVocalStart}
                            onResult={_onVocalResult}
                            lang="ko-KR"
        				/>
                    }
                    rightButtons={
                        <IconButton aria-label="전송" onClick={handClick}><SendIcon/></IconButton>
                    }
                />
            </CardContent>
        </Card>
    );
};

export default Vchat;
