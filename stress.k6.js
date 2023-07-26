import { check, sleep } from 'k6';
import http from 'k6/http';
import { Gauge } from 'k6/metrics';

export const options = {
    stages: [
        {target: 1, duration: '5s'},
        {target: 20, duration: '30s'},
        {target: 20, duration: '30s'},
        {target: 0, duration: '5s'}
    ]
};

const counts = new Gauge('timing_count');

export default function() {
    const funcRes = http.get('http://localhost:8080/sites/1');
    counts.add(funcRes.timings.waiting);
    check(funcRes, { 'req_success': r => r.status === 200 });
    sleep(0.04);

    const res = http.get('http://localhost:8080/ann/sites/1');
    counts.add(res.timings.waiting);
    check(res, { 'req_success': r => r.status === 200 });
    sleep(0.04);
}
