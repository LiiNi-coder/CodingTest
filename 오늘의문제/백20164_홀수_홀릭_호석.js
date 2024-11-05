const { log } = require("console");
const readline = require("readline");
let rl = readline.createInterface({input:process.stdin, output:process.stdout});
const lines = [];
rl.on("line", (line)=>{
    lines.push(line.trim());
});
rl.on("close", ()=>{
   solution(lines); 
});
class deque{
    constructor(){
        this.stroage = {};
        this.front = 0;
        this.rear = 0;
    }
    size(){
        return this.rear - this.front;
    }
    append(element){
        this.stroage[this.rear++] = element;
    }
    popleft(){
        if(this.front === this.rear){
            this.front = this.rear = 0;
            return null;
        }
        let popped_element = this.stroage[this.front];
        delete this.stroage[this.front];
        this.front++;
        return popped_element;
    }
}
function combination(arr, r){
    result = [];
    function dfs(sub_arr, index){
        if(index > arr.length){
            return;
        }
        if(sub_arr.length === r){
            result.push(sub_arr);
            return;
        }
        for(let i = index; i<arr.length; i++){
            dfs([...sub_arr, arr[i]], i+1);
        }
    }
    dfs([], 0);
    return result;
}
function getSubsN(n, n_parts){
    const n_str = String(n);
    const results = [];
    let temp = [];
    for(let i = 1; i<n_str.length; i++)
        temp.push(i);
    for(const inner_is of combination(temp, n_parts-1)){
        inner_is.splice(0, 0, 0);
        inner_is.push(n_str.length);
        temp = [];
        for(let i = 0; i<inner_is.length - 1; i++)
            temp.push(Number(n_str.slice(inner_is[i], inner_is[i+1])));
        results.push([...temp]);
    }
    return results;
}
function getOdd(n){
    let count = 0;
    for (const c of String(n)) {
        if( Number(c) % 2 === 1){
            count += 1;
        }
    }
    return count;
}
async function solution(lines){
    const N = Number(lines[0]);
    const q = new deque();
    const answers = [];
    q.append([N, 0]);
    while(q.size() > 0){
        //await new Promise(resolve => setTimeout(resolve, 1000));
        //console.log(q);
        
        let [n, count] = q.popleft();
        //console.log(`n:${n}, count:${count}`);
        
        let n_len = String(n).length;
        count += getOdd(n);
        if(n_len >=3){
            let temp = getSubsN(n, 3);
            //console.log(temp);
            for (const subs of temp) {
                q.append([subs.reduce((p, c)=>p+c, 0), count]);
            }
        }
        else if(n_len === 2){
            q.append([Math.floor(n/10) + n%10, count]);
        }
        else if(n_len === 1){
            answers.push(count);
        }
    }
    console.log(`${Math.min(...answers)} ${Math.max(...answers)}`);    
}