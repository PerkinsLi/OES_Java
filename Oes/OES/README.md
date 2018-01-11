# Command line instructions


# Git global setup
````sh
git config --global user.name "Danny Wang"
git config --global user.email "dannywang@augmentum.com.cn"
```

# Create a new repository

```sh
git clone git@git.augmentum.com.cn:Duke.He/20170710-yz-dev.git
cd 20170710-yz-dev
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master
```

# Existing folder

```sh
cd existing_folder
git init
git remote add origin git@git.augmentum.com.cn:Duke.He/20170710-yz-dev.git
git add .
git commit
git push -u origin master
```

# Existing Git repository

```sh
cd existing_repo
git remote add origin git@git.augmentum.com.cn:Duke.He/20170710-yz-dev.git
git push -u origin --all
git push -u origin --tags
```