############################################################
#                                                          #
#  DD2458 Problem Solving and Programming Under Pressure   #
#       Lab 1 - Problem 1.6: Rational Arithmetic           #
#          Eduardo Rodes Pastor (9406031931)               #
#                                                          #
############################################################

from fractions import Fraction


class Rational:
    ''' A rational representation class with the ability of solving basic arithmetic operations,
    compare two rational numbers and reduce a rational number to lowest terms. '''

    def __init__(self, x1, y1, x2=1, y2=1, op=None):
        ''' Initializes the class with two rational numbers (x1/x2) and (y1/y2) and an arithmetic operation
        if any. Denominators are set to 1 by default. '''
        self.x1 = x1
        self.x2 = x2
        self.op = op
        self.y1 = y1
        self.y2 = y2
        self.available_operations = {'+': self.add, '-': self.substract, '*': self.multiply, '/': self.divide} 

    def getFirstTerm(self):
        ''' Returns the reduced fracion for the rational number x1/x2 already stored. '''
        return Fraction(self.x1, self.x2)

    def getSecondTerm(self):
        ''' Returns the reduced fracion for the rational number y1/y2 already stored. '''
        return Fraction(self.y1, self.y2)

    def add(self, first_term, second_term):
        ''' Returns the result of an addition operation between two terms. '''
        return Fraction(first_term + second_term)

    def substract(self, first_term, second_term):
        ''' Returns the result of a substraction operation between two terms (substracts the second term to the first one). '''
        return Fraction(first_term - second_term)

    def multiply(self, first_term, second_term):
        ''' Returns the result of a multiplication operation between two terms. '''
        return Fraction(first_term * second_term)

    def divide(self, first_term, second_term):
        ''' Returns the result of a division operation between two terms. '''        
        return Fraction(first_term / second_term)

    def compare(self):
        ''' Compares the two rational numbers x1/x2 and y1/y2 already stored. Returns true if they have the same value
        or false otherwise. '''
        return self.getFirstTerm() == self.getSecondTerm()

    def lowestTerms(self):
        ''' Returns the reduced fracion for the rational number x1/y1 already stored. '''
        return Fraction(self.x1, self.y1)

    def solveOperation(self):
        ''' Returns the result of the operation already stored between the rational numbers x1/x2 and y1/y2 already
        stored. '''
        return self.available_operations[self.op](self.getFirstTerm(), self.getSecondTerm())