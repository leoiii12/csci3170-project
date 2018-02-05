# Make tunnel between your computer and the cse server
```sh
UNIX_USER_NAME="mkchoi6"
ssh -L 2312:projgw.cse.cuhk.edu.hk:2312 ${UNIX_USER_NAME}@linux1.cse.cuhk.edu.hk
```