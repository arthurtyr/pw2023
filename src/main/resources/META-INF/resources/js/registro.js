function get(){
    const getlog = getLog();
    fetch(getlog)
        .then((response) =>{
            if(response.status === 200){
                return response.json();
            }else{
                throw new Error("Ocorreu algum erro no servidor");
            }
        })
        .then(json =>{
            let tabela = document.getElementById("ctabela");

            for (let i = 0; i < json.entity.length; i++) {
                let row = tabela.insertRow();

                row.insertCell().innerText = json.entity[i].usuario;
                row.insertCell().innerText = json.entity[i].acao;
                row.insertCell().innerText = json.entity[i].data;
            }
        })
}

function getLog(){
    return new Request("http://localhost:8080/listarlog", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
}