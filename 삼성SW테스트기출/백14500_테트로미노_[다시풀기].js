const { normalize } = require("path");
const readline = require("readline");
const rl = readline.createInterface({input:process.stdin, output:process.stdout});
const lines = [];
rl.on("line", (line)=>{
    lines.push(line);
});
rl.on("close", ()=>{
    solution(lines);
});
let [N, M] = [null, null];
const N_BLOCK = 4;
function getNewSet(src){
    const des = new Set();
    for (const element of src) {
        des.add(element);
    }
    return des;
}
function getMaxAt(arr, r, c){
    const results = [];
    function recur(_r, _c, _sum, _routes){
        if(!_routes.has([_r, _c].toString())){
            _routes.add([_r, _c].toString());
            _sum += arr[_r][_c];
        }
        if(_routes.size === N_BLOCK){
            results.push([_routes, _sum]);
            return;
        }
        for (const [dr, dc] of [[0, 1], [1, 0], [0, -1], [-1, 0]]) {
            const [nr, nc] = [_r+dr, _c+dc];
            if(nr<0 || nc<0 || nr >=N || nc >=M)
                continue;
            if(_routes.has([nr, nc].toString()))
                continue;
            recur(nr, nc, _sum, getNewSet(_routes));
        }
    }
    recur(r, c, 0, new Set());
    return Math.max(...results.map(v=>v[1]));
}
function solution(lines){
    [N, M] = lines[0].split(" ").map((value)=>Number(value));
    const MAPS = [];
    for (let i = 1; i < lines.length; i++) {
        const element = lines[i].split(" ").map((value)=>Number(value));
        MAPS.push(element);
    }
    
    const results = [];
    for(let r = 0; r<N; r++){
        for(let c = 0; c<M; c++){
            results.push(getMaxAt(MAPS, r, c));
        }
    }
    console.log(Math.max(...results));
}