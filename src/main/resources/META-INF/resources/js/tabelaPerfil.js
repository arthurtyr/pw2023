function criarTabela(lista){
    const corpoTabela = document.getElementById("ctabela");

    const linha = corpoTabela.insertRow();
    const lnomeperfil = linha.insertCell(0);
    const ltrocanomeperfil = linha.insertCell(1);
    const lacessolog = linha.insertCell(2);
    const lcriarficha = linha.insertCell(3);

    lnomeperfil.innerText = nomeperfil;
    ltrocanomeperfil.innerText = trocanomeperfil;
    lacessolog.innerText = acessolog;
    lcriarficha.innerText = criarficha;
}

function get(){
    const getperfil = getPerfil();
    fetch(getperfil)
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
                row.insertCell().innerText = json.entity[i].acessolog;
                row.insertCell().innerText = json.entity[i].excluirusuario;
            }
        })
}

function getPerfil(){
    return new Request("http://localhost:8080/listar", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
}