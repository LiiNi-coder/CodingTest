const readline = require("readline");
const rl = readline.createInterface({input:process.stdin, output:process.stdout});
const lines =[];
rl.on("line", (line)=>{
    lines.push(line);
});
rl.on("close", ()=>{
    solution(lines);
});
let N;
function getPermutations(arr, r){
    const results = [];
    const visited = new Array(arr.length).fill(false);
    function recur(sub_arr){
        if(sub_arr.length === r){
            results.push(sub_arr);
            return;
        }
        for (let i = 0; i < arr.length; i++) {
            //if(visited[i])
                //continue;
            visited[i] = true;
            recur([...sub_arr, arr[i]]);
            visited[i] = false;
        }
    }
    recur([]);
    return results;
}

function move(maps, id_move){
    
    function handleLine(line){
        const result = [];
        let prev = null;
        for (const number of line) {
            if(number === 0) continue;
            if(!prev) prev = number;
            else{
                if(prev === number){
                    result.push(prev*2);
                    prev = null;
                }else{
                    result.push(prev);
                    prev = number;
                }
            }
        }
        if(prev) result.push(prev);
        while(result.length < line.length) result.push(0);
        return result;
    }

    let target_line = [];
    if(id_move === 0){
        for (let c = 0; c < N; c++) {
            for(let r = 0; r<N; r++){
                target_line.push(maps[r][c])
            }
            const temp = handleLine(target_line);
            for(let r = 0; r<N; r++){
                maps[r][c] = temp[r];
            }
            target_line = [];
        }
    }else if(id_move === 1){
        for (let r = 0; r < N; r++) {
            for(let c = N-1; c>=0; c--){
                target_line.push(maps[r][c])
            }
            const temp = handleLine(target_line);
            for(let c = N-1; c>=0; c--){
                maps[r][c] = temp[c];
            }
            target_line = [];
        }
    }else if(id_move === 2){
        for (let c = 0; c < N; c++) {
            for(let r = N-1; r>=0; r--){
                target_line.push(maps[r][c])
            }
            const temp = handleLine(target_line);
            for(let r = N-1; r>=0; r--){
                maps[r][c] = temp[r];
            }
            target_line = [];
        }
    }else if(id_move === 3){
        for (let r = 0; r < N; r++) {
            for(let c = 0; c < N; c++){
                target_line.push(maps[r][c])
            }
            const temp = handleLine(target_line);
            for(let c = 0; c < N; c++){
                maps[r][c] = temp[c];
            }
            target_line = [];
        }
    }
}

function solution(lines){
    N = Number(lines[0]);
    const MAPS = [];
    let max = 0;
    for (let i = 1; i < lines.length; i++) {
        const element = lines[i].split(" ").map((value)=>Number(value));
        MAPS.push(element);
    }

    function copyMatrix(arr){
        const result = [];
        for(let i = 0; i<arr.length; i++){
            const line = [];
            for(let j = 0; j<arr[i].length; j++)
                line.push(arr[i][j]);
            result.push(line);
        }
        return result;
    }

    for (const combinations of getPermutations([0, 1, 2, 3], 5)) {
        let maps = copyMatrix(MAPS);
        for (const id_move of combinations) {
            move(maps, id_move);
        }
        for (let r = 0; r < N; r++) {
            let temp = Math.max(...maps[r]);
            if(max < temp) max = temp;
        }
    }
    console.log(max);
    
}