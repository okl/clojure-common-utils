(defn double [number]
  (* 2 number))

(defn double-a-list [list-of-numbers]
  (map double list-of-numbers))

(double-a-list [1 2 24 50]) ;; => [2 4 48 100]

(defn transform-a-list [your-function list-of-numbers]
  (map your-function list-of-numbers))


(defn special-fun [my-fun your-fun & things]
  (my-fun (first things) (your-fun (rest things))))

(special-fun + average [1 2 3 4])
 ;; => (+ (first [1 2 3 4]) (average (rest [1 2 3 4])))
 ;; => (+ 1 (average (rest [1 2 3 4])))
 ;; => (+ 1 (average [2 3 4]))
 ;; => (+ 1 3)
 ;; => 4


(defn project-map [some-map & {:keys [key-xform value-xform]}]
  (let [
        xform-assoc!
        (fn xform-assoc! [some-map kv]
          (assoc! some-map (key-xform (key kv)) (value-xform (val kv))))]
    (persistent!
     (loop [kvs (seq some-map)
            new-map (transient {})]
       (if (empty? kvs)
         new-map
         (recur (rest kvs)
                (xform-assoc! new-map (first kvs))))))))
