<template>
  <div class="home">
    <a href="/user">사용자 목록</a>
    <br>
    <input type="text" name="userid" id="userid" placeholder="사용자 아이디를 입력하세요">
    <br>
    <input type="password" name="userpwd" id="userpwd" placeholder="비밀번호를 입력하세요">
    <br>
    <button @click="login">로그인</button>
    <button @click="getData">데이터 호출</button>
    <h3><a @click="download('새 텍스트 문서.txt')">File Download</a></h3>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HomeView',
  methods: {
    login() {
      // 로그인 기능을 여기에 추가하세요
      // 예를 들어, 사용자가 입력한 아이디와 비밀번호를 가져와서 서버에 전달하는 등의 작업을 수행할 수 있습니다.
    },
    getData(){
      axios.get('/json').then((response)=> {
        console.log(response)
      }) 
      .catch((error) => {
        console.log(error)
      })
    },
    download(filename){
      axios.post('/api/file/download', null, { params :{
						filename: filename,
					}}
				)
				.then(result => {
					const disposition = result.headers['content-disposition'];
					const encodedFilename = disposition.split("UTF-8''")[1];
					const filename = decodeURIComponent(encodedFilename);
					const url = window.URL.createObjectURL(new Blob([result.data]));
					const link = document.createElement('a');
					link.href = url;
					link.setAttribute('download', filename);
					document.body.appendChild(link);
					link.click();
				})
				.catch(error => {
					if (error.response && error.response.status === 404) {
						console.log('File Not Found.');
					}
				});
    }
  }
}
</script>

<style>
.home {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  text-align: center;
}

a {
  color: black;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.5rem;
  margin-bottom: 20px;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}
</style>
