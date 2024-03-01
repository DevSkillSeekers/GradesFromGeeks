package com.solutionteam.mindfulmentor.data.utils

class UserAlreadyExistsException(message: String) : Exception(message)

class UserNotSignedInException : Exception("No user is currently signed in")
class UserNotFoundException : Exception("User not found")