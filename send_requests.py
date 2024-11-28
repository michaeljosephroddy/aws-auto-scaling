from concurrent.futures import ThreadPoolExecutor
import requests

url = "http://assignment1autoscalinggroup-1-lb-1645138976.eu-west-1.elb.amazonaws.com/couponapi/generateload"

def send_request(request_id):
    try:
        response = requests.get(url)
        print(f"Request {request_id}: Status Code {response.status_code}")
        print(f"Response: {response.text}\n")
    except requests.exceptions.RequestException as e:
        print(f"Request {request_id} failed: {e}")

total_requests = 10000
concurrent_requests = 5

try:
    with ThreadPoolExecutor(max_workers=concurrent_requests) as executor:
        executor.map(send_request, range(1, total_requests + 1))

except KeyboardInterrupt:
    executor.shutdown(wait=False)
    print("\nProgram terminated by user.")