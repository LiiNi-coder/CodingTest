/**
 *  백준 4358 생태학
 *  https://www.acmicpc.net/problem/4358
 *  기본적인 객체 프로퍼티 추가 및, 프로퍼티별로 정렬방법
 */

const readline = require("readline");
let rl = readline.createInterface({input:process.stdin, output:process.stdout});
const lines = [];
rl.on("line", (line)=>{
    lines.push(line.trim());
});
rl.on("close", ()=>{
    solution(lines);
});
function solution(lines){
    const name_count = {};
    let all_count = 0;
    for(let name of lines){
        if(!name)
           continue;
        if(!(name in name_count)){
            name_count[name] = 0;
        }
        name_count[name] += 1;
        all_count ++;
    }
    let temp = [];
    for(let name of Object.keys(name_count)){
        temp.push([name, (name_count[name]/all_count*100).toFixed(4)]);
    }
    temp.sort((a,b)=>a[0].localeCompare(b[0]));
    for(let [name, n] of temp){
        console.log(`${name} ${n}`);
    }
    return;
}
