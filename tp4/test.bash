javac Main.java

for i in {1..5}; do
    echo "Testcase $i"
    java Main < "in$i" > "my$i"
    time diff --strip-trailing-cr "my$i" "out$i"
done
