(ns sketch.dynamic
  (:require [clojure.pprint :as pretty]
            [quil.core :as quil]))

(def colours {:base03 [193 100 21]
              :base02 [192 90 26]
              :base01 [194 25 46]
              :base00 [195 23 51]
              :base0 [186 13 59]
              :base1 [180 9 63]
              :base2 [44 11 93]
              :base3 [44 10 99]

              :yellow [45 100 71]
              :orange [8 89 80]
              :red [1 79 86]
              :magenta [331 74 83]
              :violet [237 45 77]
              :blue [205 82 82]
              :cyan [175 74 63]
              :green [68 100 60]})

(defn save-frame-to-disk
  ([]
   (quil/save-frame (pretty/cl-format nil
                                      "frames/~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-####.jpeg"
                                      (quil/year) (quil/month) (quil/day)
                                      (quil/hour) (quil/minute) (quil/seconds))))
  ([state _]
   (save-frame-to-disk)
   state))

(defn- stroke
  [colour]
  (apply quil/stroke (flatten (conj (colours colour) (rand)))))

(defn- point
  []
  [(quil/random 0 (quil/width))
   (quil/random 0 (quil/height))])

(defn- shade
  []
  (stroke :base03)
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
