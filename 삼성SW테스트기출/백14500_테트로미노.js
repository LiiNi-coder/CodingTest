const readline = require("readline");
const rl = readline.createInterface({input:process.stdin, output:process.stdout});
const lines = [];
rl.on("line", (line)=>{
    lines.push(line);
});
rl.on("close", ()=>{
    solution(lines);
});

function getSumNM(arr, r, c, n, m){
    let sum = 0;
    for(let i = r; i<r+n; i++){
        for(let j = c; j<c+m; j++)
            sum += arr[i][j];
    }
    return sum;
}
const tetrimino = [
    [1, 4, (arr, r, c)=>{
        return getSumNM(arr, r, c, 1, 4);
    }],
    [4, 1, (arr, r, c)=>{
        return getSumNM(arr, r, c, 4, 1);
    }],
    [2, 2, (arr, r, c)=>{
        return getSumNM(arr, r, c, 2, 2);
    }],
    [3, 2, (arr, r, c)=>{
        let sums = getSumNM(arr, r, c, 3, 2);
        const temp = [];
        for (const [a, b] of [[[0, 0], [1, 0]], [[0, 1], [1, 1]], [[1, 0], [2, 0]], [[1, 1], [2, 1]], [[0, 0], [2, 0]], [[0, 1], [2, 1]],[[0, 0], [2, 1]],[[0, 1], [2, 0]]]) {
            temp.push(sums - arr[r+a[0]][c+a[1]] - arr[r+b[0]][c+b[1]]);
        }
        return Math.max(...temp);
    }],
    [2, 3, (arr, r, c)=>{
        let sums = getSumNM(arr, r, c, 2, 3);
        const temp = [];
        for (const [a, b] of [[[0, 0], [0, 1]], [[1, 0], [1, 1]], [[0, 1], [0, 2]], [[1, 1], [1, 2]], [[0, 0], [0, 2]], [[1, 0], [1, 2]],[[0, 0], [1, 2]],[[0, 2], [1, 0]]]) {
            temp.push(sums -  arr[r+a[0]][c+a[1]] - arr[r+b[0]][c+b[1]]);
        }
        return Math.max(...temp);
    }]
    

];

function solution(lines){
    const [N, M] = lines[0].split(" ").map((value)=>Number(value));
    const MAPS = [];
    for (let i = 1; i < lines.length; i++) {
        const element = lines[i].split(" ").map((value)=>Number(value));
        MAPS.push(element);
    }
    
    //console.log(getSumNM(MAPS,0, 0, 3, 2));
    

    const results = [];
    for (const [a, b, callbacks] of tetrimino) {
        //console.log(a, b, callbacks);
        const temp = [];
        for(let i = 0; i<N - (a-1); i++)
            for(let j = 0; j<M - (b-1); j++){
                temp.push(callbacks(MAPS, i, j));
            }
        //console.log(temp);
        results.push(Math.max(...temp));
    }
    console.log(Math.max(...results));
    
    
}