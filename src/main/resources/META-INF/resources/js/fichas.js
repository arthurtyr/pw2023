function get(){
    const getficha = getFicha();
    fetch(getficha)
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

                row.insertCell().innerText = json.entity[i].nome;
                row.insertCell().innerText = json.entity[i].nivel;
                row.insertCell().innerText = json.entity[i].destreza;
                row.insertCell().innerText = json.entity[i].constituicao;
                row.insertCell().innerText = json.entity[i].inteligencia;
            }
        })
}

function getFicha(){
    return new Request("http://localhost:8080/listarfichas", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
}