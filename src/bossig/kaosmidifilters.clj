(ns bossig.core)


(on-event [:midi :timing-clock]
          (fn [e]
            (println e)
            ;(println  (:channel e) )
            ;(print " ")
            ;(print (:note e) )
            ;(print " ")


            )
          ::clock-handler
          )


(on-event [:midi :midi-time-code]
          (fn [e]
           ; (println e)
            ;(println  (:channel e) )
            ;(print " ")
            ;(print (:note e) )
            ;(print " ")


            )
          ::clock-handler2
          )

(remove-event-handler ::clock-handler)


;;midimaps
(def midimap8 (atom []))
(def midimap8map (atom []))
(def midichords (atom []))

(on-event [:midi :note-on]
          (fn [e]
            ;;(println e)
            ;;(println "channel  ")
            ;;(print  (:channel e) )
            ;;(print "note ")
            ;;(print (:note e) )
            ;;
            ;;(print " ")


            (let [note (:note e)
                  vel  (:velocity e)
                  channel (:channel e)]
              ;;(println channel note vel)
              (if (and (= channel 15) (= note 60))
                (swap! bbeat inc)
                                        ;(concat (print "note =") (println  note ))
                (if (= channel 0)
                                        ;(println "bd in ta house")
                  (swap! bd assoc :note note :velocity vel)
                                        ;(println (concat (print "stuff = ") (print note)))
                  (if (= channel 1)
                    (swap! sd assoc :note note :velocity vel)
                    (if (= channel 2)
                      (swap! ch  assoc :note note :velocity vel)
                      (if (= channel 3)
                        (swap! oh assoc :note note :velocity vel)
                        (if (= channel 4)
                          (swap! pc1 assoc :note note :velocity vel)
                          (if (= channel 5)
                            (swap! pc2 assoc :note note :velocity vel)
                            (if (= channel 6)
                              (swap! ld1 assoc :note note :velocity vel)
                              (if (= channel 7)
                                (swap! ld2 assoc :note note :velocity vel)
                                (if (= channel 8)
                                  (do
                                    (if (= false (.contains @midimap8 note))
                                      (do ;(println midimap8)
                                        (swap! midimap8 conj note)
                                        (reset! midimap8 (vec (sort @midimap8)))
                                        (swap! midimap8map conj {:x (rand-int width) :y (rand-int height) :z (rand-int 500) })
                                        (reset! midichords [{:channel :chords} @midimap8 @midimap8map])
                                        ) )

                                    (swap! chords assoc :note note :velocity vel))
                                  (if (= channel 9)
                                    (swap! keyz assoc :note note :velocity vel) )))))))))))))
          ::keyboard-handler)


;; (defn midiparse [midimap midimapmap note channelname]
;;   (do
;;     (if (= false (.contains midimap note))
;;       (do ;(println midimap8)
;;         (swap! midimap8 conj note)
;;         (reset! midimap8 (vec (sort @midimap8)))
;;         (swap! midimap8map conj {:x (rand-int width) :y (rand-int height) :z (rand-int 500) })
;;         (reset! midichords [{:channel :chords} @midimap8 @midimap8map])
;;         ) )

;;     (swap! chords assoc :note note :velocity vel)))


;; (on-event [:midi :note-on]
;;           (fn [e]
;;               (let [note (:note e)
;;                     vel  (:velocity e)
;;                     channel (:channel e)]
;;                 (case channel
;;                   1 (mapmidi )
;;                   )

;;                 )
;;               )
;;           ::keyboard-handler)

(on-event [:midi :control-change]
          (fn [e]
            ;(println e)
            (let [data1 (:data1 e)
                  data2 (:data2 e)
                  channel (:channel e)]
              (if (and  (= channel 2) (= data1 10))
                (swap! ch assoc :pan data2)
                )
              )
            )
          ::control-handler
          )
