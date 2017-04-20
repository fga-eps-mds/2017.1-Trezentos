if [[ "$TRAVIS_BRANCH" == "production" ]]; then
    ./gradlew build

    fastlane deploy

    exit $?
else
    ./gradlew build

    exit $?
fi
