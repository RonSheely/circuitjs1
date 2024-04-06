const { statSync } = require('node:fs');
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout,
    prompt: 'your answer: '
})
const mvn = require('maven').create({cwd: '.'});
const child_process = require('child_process');
const { resolve } = require('node:path');

const nw_version = '0.64.1-mod1';
//const nw_version = '0.20.0';

const menu="\n1 - check steps      | 2 - run devmode        | 3 - run GWT app\n"
            +"4 - build GWT app    | 5 - get all nw.js bin  | 6 - build from all bin\n"
            +"7 [x32,x64] - (win)* | 8 [x32,x64] - (linux)* | 9 [x64] - (macOS)*\n"
            //+"'7 [x32,x64]' - build from bin for windows only\n"
            //+"'8 [x32,x64]' - build from bin for linux only\n"
            //+"'9 [x64,arm64]' - build from bin for macOS only\n"
            +"0 - exit             |       full - full build for all platforms \n"
            +"(* - build from bin for windows (win), linux or macOS only)\n";

const stepNames = [
    "Devmode (mvn gwt:devmode)",
    "Build GWT web app (mvn clean install)",
    "├── nwjs-v"+nw_version+"-win-ia32.zip",
    "├── nwjs-v"+nw_version+"-win-x64.zip",
    "├── nwjs-v"+nw_version+"-linux-ia32.tar.gz",
    "├── nwjs-v"+nw_version+"-linux-x64.tar.gz",
    //"├── nwjs-v"+nw_version+"-osx-arm64.zip",
    "└── nwjs-v"+nw_version+"-osx-x64.zip",
    "├── win-ia32",
    "├── win-x64",
    "├── linux-ia32",
    "├── linux-x64",
    //"├── osx-arm64",
    "└── osx-x64"
]

const pathsToCheck = [
    './war/circuitjs1/setuplist.txt',
    './target/site/circuitjs1/setuplist.txt',
    './nwjs_cache/nwjs-v'+nw_version+'-win-ia32',
    './nwjs_cache/nwjs-v'+nw_version+'-win-x64',
    './nwjs_cache/nwjs-v'+nw_version+'-linux-ia32',
    './nwjs_cache/nwjs-v'+nw_version+'-linux-x64',
    //'./nwjs_cache/nwjs-v'+nw_version+'-osx-arm64',
    './nwjs_cache/nwjs-v'+nw_version+'-osx-x64',
    './out/win-ia32',
    './out/win-x64',
    './out/linux-ia32',
    './out/linux-x64',
    //'./out/osx-arm64',
    './out/osx-x64'
]

let stateOfSteps = new Array(13);
let diffTimes_ms = new Array(13); // diff between mtime and current time

function getFileNumber(platform, arch){
    let i = 7;
    let j = (arch == "x64" && platform!="osx") ? 1 : 0;
    switch (platform) {
        case "win": return i+j;
        case "linux": return 2+i+j;
        case "osx": return 4+i;
    }
}

function checkSteps() {

    lastStep = -1;
    let lastStepTime = -1;
    let currentTime = new Date();

    function getTimeAgoInfo(time){
        time/=1000;     // milliseconds to seconds
        if (time<60) {return Math.floor(time)+" seconds ago"}
        else if (time<60**2){
            let time_m = Math.floor(time/60);
            let time_s = Math.floor(time-time_m*60);
            return time_m+" min. "+time_s+" sec. ago";
        }
        else if (time<24*(60**2)){
            let time_h = Math.floor(time/60**2);
            let time_m = Math.floor((time-time_h*(60**2))/60);
            let time_s = Math.floor(time-time_h*(60**2)-time_m*60);
            return time_h+" h. "+time_m+" min. "+time_s+" sec. ago";
        }
        else{
            let time_d = Math.floor(time/(24*(60**2)));
            let time_h = Math.floor((time-time_d*24*(60**2))/60**2)
            let time_m = Math.floor((time-time_d*24*(60**2)-time_h*(60**2))/60)
            let time_s = Math.floor(time-time_d*24*(60**2)-time_h*(60**2)-time_m*60);
            return time_d+" d. "+time_h+" h. "+time_m+" min. "+time_s+" sec. ago";
        }
    }

    for (let i = 0; i < pathsToCheck.length; i++) {
        try{
            let statInfo = statSync(pathsToCheck[i]);
            stateOfSteps[i] = true;
            diffTimes_ms[i] = currentTime.getTime()-statInfo.mtime.getTime();
            if (lastStepTime>diffTimes_ms[i] || lastStepTime<0){
                lastStep = i;
                lastStepTime = diffTimes_ms[i];
            }
        }catch{
            stateOfSteps[i] = false;
            diffTimes_ms[i] = null;
        }
    }

    let x = stateOfSteps;
    //isDownloadComplite = x[2]&x[3]&x[4]&x[5]&x[6]&x[7]; //with arm64
    //isLastBinaryComplite = x[8]&x[9]&x[10]&x[11]&x[12]&x[13]; //with arm64
    isDownloadComplite = x[2]&x[3]&x[4]&x[5]&x[6];
    isLastBinaryComplite = x[7]&x[8]&x[9]&x[10]&x[11];

    const completedInfo = "[✔] ";
    const notCompletedInfo = "[✘] ";

    console.log();
    console.log("Check steps:");
    for (let i = 0; i < stepNames.length; i++) {
        let getLastСhangeInfo = (i==lastStep) ? " ◀- LAST" : "";
        let getCompletedStateInfo = (stateOfSteps[i]) ? completedInfo : notCompletedInfo;
        let basicInfo = getCompletedStateInfo+stepNames[i];
        if (stateOfSteps[i])
            console.info(basicInfo+" - "+getTimeAgoInfo(diffTimes_ms[i])+getLastСhangeInfo)
        else
            console.warn(basicInfo)
        //if (i==1 || i==7){ //if arm64
        if (i==1 || i==6){
            let isCompleted = (i==1) ? isDownloadComplite : isLastBinaryComplite;
            let getCompletedInfo = (isCompleted) ? completedInfo : notCompletedInfo;
            let basicInfo = (i==1) ?
            getCompletedInfo+"GET NW.JS BINARIES" :
            getCompletedInfo+"BUILD CIRCUITJS1 DESKTOP MOD";
            if (isCompleted) console.info(basicInfo)
            else console.warn(basicInfo);
        }
    }

    console.log();

}

function runDevmode(){

    return Promise.all([

        async function(){
        const interval = setInterval(() => {
            fetch("http://127.0.0.1:8888")
            .then(function (response) {
                if (response.status >= 200 && response.status < 300) {
                    clearInterval(interval);
                    child_process.spawn(
                    require('nw').findpath(),
                    ['./scripts/devmode'],
                    {
                        cwd: ".",
                        detached: true,
                        stdio: 'ignore'
                    }
                ).unref();
                    return console.log(response.status);
                }
            })
            .catch ((error) => {})}
        , 2000);
        }(),

        mvn.execute("gwt:devmode", { 'skipTests': true }).then(() => {
            console.log("Devmode completed successfully!");
            checkSteps();
        })

    ])

}

function runGWT(){
    if (!stateOfSteps[1])
        return console.error("\nGWT app is not compiled!\n"
        + "Please run \"4 - build GWT app\"");
    child_process.spawn(
        require('nw').findpath(),
        ['./target/site'],
        {
            cwd: ".",
            detached: true,
            stdio: 'ignore'
        }
    ).unref();
}

function buildGWT(){
    return Promise.all([
        mvn.execute(['clean', 'install'], { 'skipTests': true }).then(() => {
            console.log("GWT app completed successfully!");
            checkSteps();
        })
    ])
}

async function getBin(platform, arch){
    
    // let archiveType = (platform = "linux") ? ".tar.gz" : ".zip";
    // let archivePath = pathsToCheck[getFileNumber(platform, arch)-5]+archiveType;

    console.log("NW.js download for "+platform+" "+arch+" has started. Please wait...");
    let nwbuild = (await import("nw-builder")).default;
    await nwbuild({
        mode: "get",
        version: nw_version,
        platform: platform,
        arch: arch,
        flavor: "normal",
        downloadUrl: "https://github.com/SEVA77/nw.js_mod/releases/download",
        manifestUrl: "https://raw.githubusercontent.com/SEVA77/nw.js_mod/main/versions.json",
        cacheDir: "./nwjs_cache",
        srcDir: "target/site"
    }).catch(()=>{/*console.error("ERROR: The archive "+archivePath.slice(13)+" is damaged. Remove it and try again.")*/})
    .then(()=>{console.log("Download for "+platform+" "+arch+" has been completed!")})
}

async function buildRelease(platform, arch){
    let nwbuild = (await import("nw-builder")).default;
    console.log("Building release for "+platform+" "+arch+" has started");
    //let archiveType = (platform == "linux") ? "tgz" : "zip";
    await nwbuild({
        mode: "build",
        version: nw_version,
        platform: platform,
        arch: arch,
        flavor: "normal",
        cacheDir: "./nwjs_cache",
        manifestUrl: "https://raw.githubusercontent.com/SEVA77/nw.js_mod/main/versions.json",
        srcDir: "target/site",
        outDir: "./out/"+platform+"-"+arch+"/CircuitJS1 Desktop Mod",
        glob: false,
        //zip: archiveType    // the archiver in my system is better
    });
}

function getAllBins(){
    // TODO: Make archive checker
    return Promise.all([
        getBin('win', 'ia32'),
        getBin('win', 'x64'),
        getBin('linux', 'ia32'),
        getBin('linux', 'x64'),
        getBin('osx', 'x64')
    ]);
}

function buildAll(){
    // TODO: Check files and suggest overwriting them
    return Promise.all([
        buildRelease('win', 'ia32'),
        buildRelease('win', 'x64'),
        buildRelease('linux', 'ia32'),
        buildRelease('linux', 'x64'),
        buildRelease('osx', 'x64')
    ]);
}

async function platformBuild(platform,arch1,arch2){
    checkSteps();
    let k = getFileNumber(platform,arch1);
    if (!stateOfSteps[1]) await buildGWT();
    if (!stateOfSteps[k-5])
        await getBin(platform,arch1);
    await buildRelease(platform,arch1);
    if (arch2!==undefined) {
        if (!stateOfSteps[k-5])
            await getBin(platform,arch2);
        await buildRelease(platform,arch2);
    }
}

async function fullBuild(){
    await buildGWT();
    await getAllBins();
    await buildAll();
}

function Menu() {

    console.log(menu);
    readline.prompt();

    readline.on('line', (line) => {
    (async function(){
        switch (line.trim()) {
            default: break;
            //case 'clear': ​console.clear(); break;
            case '0': readline.close();
            case '1': checkSteps(); break;
            case '2': await runDevmode(); break;
            case '3': runGWT(); break;
            case '4': await buildGWT(); break;
            case '5': await getAllBins(); break;
            case '6': await buildAll(); break;
            case '7':
            case '7 x32 x64':
            case '7 x32,x64':
            case '7 [x32,x64]':
                console.log("RUN BUILD FOR WINDOWS X32 AND X64");
                await platformBuild('win','ia32','x64');
                break;
            case '7 x32':
            case '7 [x32]':
                console.log("RUN BUILD FOR WINDOWS X32");
                await platformBuild('win','ia32');
                break;
            case '7 x64':
            case '7 [x64]':
                console.log("RUN BUILD FOR WINDOWS X64");
                await platformBuild('win','x64');
                break;
            case '8':
            case '8 x32 x64':
            case '8 x32,x64':
            case '8 [x32,x64]':
                console.log("RUN BUILD FOR LINUX X32 AND X64");
                await platformBuild('linux','ia32','x64');
                break;
            case '8 x32':
            case '8 [x32]':
                console.log("RUN BUILD FOR LINUX X32");
                await platformBuild('linux','ia32');
                break;
            case '8 x64':
            case '8 [x64]':
                console.log("RUN BUILD FOR LINUX X64");
                await platformBuild('linux','x64');
                break;
            case '9':
            case '9 x64':
            case '9 [x64]':
                console.log("RUN BUILD FOR MAC OS X64");
                await platformBuild('osx','x64');
                break;
            case 'full': await fullBuild();
        }})()
    .then(()=>{
        console.log(menu);
        readline.prompt();
    })
    }).on('close', () => {
        console.log('\nHave a great day!\n');
        process.exit(0);
    });

}

checkSteps();
Menu()
