#ifndef HW3_CAR_H
#define HW3_CAR_H

#include <ostream>
#include <vector>
#include "Laptime.h"
#include "Utilizer.h"
class Car {

private:
    std::string driver_name;
    double performance;
    Laptime *head;
    Car *next;

    // DO NOT MODIFY THE UPPER PART
    // ADD OWN PRIVATE METHODS/PROPERTIES BELOW

public:
    /**
     * Constructor.
     *
     * @Important: set the performance variable of the car by using Utilizer::generatePerformance()
     * 
     * @param std::string The Car's driver name.
     */
    Car(std::string driver_name);
    
    /**
     * Copy Constructor.
     *
     * @param rhs The car to be copied.
     */
    Car(const Car& rhs);

    
    ~Car();
    /**
     * Gets the drivers name
     * 
     * 
     * @returns: drivers name
     */
    std::string getDriverName() const;

    /**
     * 
     * Gets the performance
     * 
     * @returns the performance
     * 
     */
    double getPerformance() const;

    /**
     * Sets the next chain for this Car.
     * Adds a new car behind existing car 
     * 
     * Important: Car does NOT "own" next.
     *
     * @param next The next Car.
     */
    void addCar(Car *next);


    /**
     * Less than overload.
     *
     * True if total laptime of this Car is less than the rhs Car.
     *
     * Important: 
     *
     * @param rhs The Car to compare.
     * @return True if this car's total laptime is smaller, false otherwise.
     */
    bool operator<(const Car& rhs) const;

    /**
     * Greater than overload.
     *
     * True if total laptime of this Car is greater than the rhs Car.
     *
     * Important: 
     *
     * @param rhs The Car to compare.
     * @return True if this car's total laptime is greater, false otherwise.
     */
    bool operator>(const Car& rhs) const;
    

    /**
     * Indexing.
     *
     * Find the laptime of the given lap.
     *
     * @return The Laptime with the given lap. Laptime with zero time if given lap does not exists.
     */
    Laptime operator[](const int lap) const;

    /**
     * Car completes one lap and records its laptime
     * 
     * @Important: Based on your cars performance calculate some variance to add average_laptime
     * Use  Utilizer::generateLaptimeVariance(performance) then add it to average_laptime
     * 
     * @param: Car takes average_laptime of the race 
     * 
     */
    void Lap(const Laptime& average_laptime);


    /**
     * Stream overload.
     *
     * What to stream:
     * First Three letters of the drivers surname(Capitalized)--Latest Laptime--Fastest Laptime--Total Laptime
     * Example:
     * For Lewis Hamilton
     * HAM--1:19.235--1:18.832--90:03.312
     *
     * @Important: for lap numbers smaller in size you have to put zeros as much as neccasary
     * @Important: you can use Laptime ostream when neccessary
     * 
     * @param os Stream to be used.
     * @param car Car to be streamed.
     * @return The current Stream.
     */
    friend std::ostream& operator<<(std::ostream& os, const Car& car);

    // DO NOT MODIFY THE UPPER PART
    // ADD OWN PUBLIC METHODS/PROPERTIES BELOW

	char getUpperCase(char param) const{
		if( (97<=param) && (param<=122)){
			return (char) (param-32);
		}
		return param;
	}
	
	std::ostream& getZeroFilled(int laptime,std::ostream& ss) const{
		int minute = laptime/60000;
		int second = (laptime - minute*60000)/1000;
		int milisecond = laptime - (minute*60000) - (second*1000);
		
		
		
		ss<<minute<<":";
		
		if(second<10){
			ss<<0;
		}
		ss<<second<<".";
		
		if(milisecond<10){
			ss<<0<<0;
		}
		else if(milisecond<100){
			ss<<0;
		}
		ss<<milisecond;
		
		
		return ss;
	}
	std::ostream& getSurname(std::string full_name,std::ostream& os) const{
		int pos=0;
		int keep_pos = -1;
		
		
		char delim = ' ';
		while( full_name[pos]!='\0'){
			if(full_name[pos] == delim){
				keep_pos = pos;
			}
			pos++;
		}
		os<<getUpperCase(full_name[keep_pos+1])<<getUpperCase(full_name[keep_pos+2])<<getUpperCase(full_name[keep_pos+3]);
		
		return os;
	}
	
	
	
	Laptime* getHead() const{
		return this->head;
	}
	
	Car* getNext() const{
		return this->next;
	}
	
	void setNext(Car* next_car){
		this->next = next_car;
	}
	
	void setPerformance(double given_performance){
		this->performance = given_performance;
	}

	
};


#endif //HW3_CAR_H
