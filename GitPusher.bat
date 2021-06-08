@echo off
title Git Pusher By Xujiayao
cd "C:\Users\Xujiayao\Desktop\Java Projects\GIFSignaturesGenerator"
git add .
echo Enter commit message: 
set /p commit_message=
git commit -m "%commit_message%"
git push origin master
start https://purge.jsdelivr.net/gh/Xujiayao/GIFSignaturesGenerator@master/update/version.json
pause