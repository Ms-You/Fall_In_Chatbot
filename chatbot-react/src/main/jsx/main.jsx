// import { Link } from "react-router-dom";
import Button from '@mui/material/Button';
import { useHistory } from "react-router-dom";

const Main = () => {
// 페이지 이동 방식
// 1. useHistory
// 2. Link to
    const history = useHistory();

    const selectStore = (num) => {
        history.push({
          pathname: "/vchat",
          props: { num:num }
        });
    };

    return(
        <div className="main">
            <h1 style = {{fontSize: '4rem'}}>통신사 챗봇 서비스</h1>
            <Button variant="contained" onClick={()=>selectStore(1)}>서울지점</Button>
            <Button variant="contained" onClick={()=>selectStore(2)}>경기지점</Button>
            {/* <Link to="/chat">
                <button>채팅 화면으로 이동</button>
            </Link> */}
        </div>
    );
};

export default Main;
