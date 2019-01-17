############################################################
#                                                          #
#  DD2458 Problem Solving and Programming Under Pressure   #
#       Lab 1 - Problem 1.6: Rational Arithmetic           #
#          Eduardo Rodes Pastor (9406031931)               #
#                                                          #
############################################################

import sys
from rational import Rational


class KattisReader:
    ''' Manages the Kattis input and output for the Rational Arithmetic problem '''

    def writeOutput(self, result):
        ''' Formats the operation result for Kattis '''
        print(str(result.numerator) + ' / ' + str(result.denominator))

    def readInput(self, line):
        ''' Reads an input line and returns a rational representation of it '''
        data = line.split()
        return Rational(int(data[0]), int(data[3]), int(data[1]), int(data[4]), data[2]) # Rational(x1, y1, x2, y2, operation)

    def main(self):
        ''' For each line in the Kattis input file, solve the operation for it and write the result in the right format '''
        next(sys.stdin) # We do not need the number of operations in the file
        for line in sys.stdin:
            kattis_input = self.readInput(line)
            self.writeOutput(kattis_input.solveOperation())

KattisReader().main()    