mv ./www/uploads ./

rm -rf ./www

mkdir -p ./www

mv ./uploads ./www/

cd ../FE-hiddenGems-heroku
npm run build

# move frontend build to static folder
mv ./dist/* ../BE-hiddenGems-heroku/www
