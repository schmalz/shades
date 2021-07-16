(ns sketch.dynamic
  (:require [clojure.pprint :as pretty]
            [quil.core :as quil]))

(defn save-frame-to-disk
  ([]
   (quil/save-frame (pretty/cl-format nil
                                      "frames/~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-####.jpeg"
                                      (quil/year) (quil/month) (quil/day)
                                      (quil/hour) (quil/minute) (quil/seconds))))
  ([state _]
   (save-frame-to-disk)
   state))

(defn- point
  []
  [(quil/random 0 (quil/width))
   (quil/random 0 (quil/height))])

(defn- shade
  []
  (quil/stroke 180 9 63 (rand)) ; base1 (grey)
  (apply quil/curve
         (flatten (take 4
                        (repeatedly point)))))

(defn draw
  []
  (quil/no-loop)
  (quil/background 44 10 99)
  (quil/fill 44 10 99)
  (dotimes [_ 3001]
    (shade))
  (save-frame-to-disk))

(defn initialise
  []
  (quil/smooth)
  (quil/color-mode :hsb 360 100 100 1.0))
