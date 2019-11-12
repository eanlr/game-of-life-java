package net.eanlr.gameoflife

import spock.lang.Specification

import static Coordinates.of

class CoordinatesSpec extends Specification {

    def "should create coordinates"() {
        when:
        def position = of(1, 2)

        then:
        position.x == 1
        position.y == 2
    }

    def "should return string representation"() {
        expect:
        of(2, 3).toString() == "(2, 3)"
    }

    def "should create coordinates with negative x"() {
        expect:
        of(-1, 0).x == -1
    }

    def "should create coordinates with negative y"() {
        expect:
        of(0, -1).y == -1
    }

    def "should create coordinates of north-east neighbour"() {
        given:
        def p = of(1, 1)

        expect:
        p.northEast() == of(2, 0)
    }

    def "should return neighbours"() {
        given:
        def p = of(0, 0)

        when:
        def n = p.neighbours()

        then:
        n.size() == 8
        n.contains(of(-1, -1))
        n.contains(of(+0, -1))
        n.contains(of(+1, -1))
        n.contains(of(-1, 0))
        n.contains(of(+1, 0))
        n.contains(of(-1, 1))
        n.contains(of(+0, 1))
        n.contains(of(-1, 1))

    }
}
