function criarPerfil(nomeperfil, trocanomeperfil, acessolog, criarficha){
    return new Request("http://localhost:8080/criarperfil", {
        method: "POST",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "nomeperfil": nomeperfil,
            "trocanomeperfil": trocanomeperfil,
            "acessolog": acessolog,
            "criarficha": criarficha
        }),
    });
}

function getPerfil(){
    return new Request("http://localhost:8080/criarperfil", {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    })
}
function check(){
    if(document.getElementById("trocanomeperfil").checked){
        document.getElementById("trocanomeperfil").value = "sim";
    }else{
        document.getElementById("trocanomeperfil").value = "nao";
    }
    if(document.getElementById("acessolog").checked){
        document.getElementById("acessolog").value = "sim";
    }else{
        document.getElementById("acessolog").value = "nao";
    }
    if(document.getElementById("criarficha").checked){
        document.getElementById("criarficha").value = "sim";
    }else{
        document.getElementById("criarficha").value = "nao";
    }
}

function perficilizar() {
        check();
        const perfil = criarPerfil(document.getElementById("nomeperfil").value,document.getElementById("trocanomeperfil").value,document.getElementById("acessolog").value,document.getElementById("criarficha").value);
        fetch(perfil)
            .then((response) =>{
                if(response.status === 201) {
                    return response.json();
                } else{
                    throw new Error("Ocorreu algum erro no servidor");
                }
            })
            .then(get)

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
            if(JSON.stringify(json) === "{\"mensagem\":\"Utilizado\"}"){
                alert("nome de perfil já utilizado");
            }else{
                const perfilinfo = JSON.stringify(json);
                const obj = JSON.parse(perfilinfo);
                criarTabela(obj.nomeperfil,obj.trocanomeperfil,obj.acessolog,obj.criarficha);
            }
        })
}

function criarTabela(nomeperfil, trocanomeperfil, acessolog, criarficha){
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
